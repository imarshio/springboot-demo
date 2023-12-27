package com.marshio.demo.annotation.springframework.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author marshio
 * @desc Transactional注解的使用，
 * 注意：这里我没有使用IService，而是直接使用了Service
 * @create 2023-12-26 16:33
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class TransactionalService {

    // 注： 这里会发生循环依赖，解决方案如下
    // 1、使用@Autowired
    // 2、对发生循环依赖的类在注入的时候加@Lazy注解
    // 3、如果使用的是@RequiredArgsConstructor，则需要改为：@RequiredArgsConstructor(onConstructor_ = {@Lazy})
    private final TransactionalService proxy;

    private final TransactionMapper mapper;

    public TransactionalService(@Lazy TransactionalService proxy, TransactionMapper mapper) {
        this.proxy = proxy;
        this.mapper = mapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public Object rollback1(Boolean flag) {
        TransactionEntity entity = new TransactionEntity();
        entity.setName("ma");
        entity.setAge(25);
        entity.setEmail("mashuo@shenqingtech.com");
        int insert = mapper.insert(entity);
        if (flag) {
            // true 执行，报错 ArithmeticException，回滚
            // false 不执行，不报错 ，不回滚
            // throw new ClassCastException();
            // throw new AssertionError();
            return insert / 0;
        }
        return insert;
    }

    @Transactional(rollbackFor = ArithmeticException.class)
    public Object rollback2(Boolean flag) {
        TransactionEntity entity = new TransactionEntity();
        entity.setName("zwl");
        entity.setAge(28);
        entity.setEmail("zwl@shenqingtech.com");
        int insert = mapper.insert(entity);

        if (flag) {
            // flag==true ,执行, 报错 ArithmeticException/ClassCastException 回滚
            // flag==false ,不执行，不报错，不回滚
            // throw new AssertionError("回滚！");
            // throw  new Exception("回滚");
            // throw new ClassCastException("回滚！");
            return insert / 0;
        }
        return insert;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Object rollback3(Boolean flag) throws Exception {
        TransactionEntity entity = new TransactionEntity();
        entity.setName("zwl");
        entity.setAge(28);
        entity.setEmail("zwl@shenqingtech.com");
        int insert = mapper.insert(entity);

        if (!flag) {
            // flag==true ,不执行
            // flag==false ,执行，报错 ArithmeticException 回滚
            return insert / 0;
        }
        if (flag) {
            // flag==true ,执行，报错BusinessException，回滚
            // flag==false ,不执行
            // throw new Exception("回滚");
            throw new AssertionError("回滚！");
            // throw new BusinessException("code", "回滚");
        }
        return insert;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Object rollback4(Boolean flag) throws BusinessException {
        TransactionEntity entity = new TransactionEntity();
        entity.setName("ma");
        entity.setAge(25);
        entity.setEmail("mashuo@shenqingtech.com");
        int insert = mapper.insert(entity);

        if (!flag) {
            // flag==true ,不执行
            // flag==false ,执行，报错 ArithmeticException 回滚
            return insert / 0;
        }
        if (flag) {
            // flag==true ,执行，报错 BusinessException，不回滚
            // flag==false ,不执行
            throw new BusinessException("code", "不回滚");
        }
        return insert;
    }


    /**
     * propagation ： 控制事务传播
     * <li>REQUIRED</li>
     *
     * @param flag 开关
     * @return
     * @see org.springframework.transaction.annotation.Propagation#REQUIRED
     * <li>SUPPORTS</li>
     * @see org.springframework.transaction.annotation.Propagation#SUPPORTS
     * <li>MANDATORY</li>
     * @see org.springframework.transaction.annotation.Propagation#MANDATORY
     * <li>REQUIRES_NEW</li>
     * @see org.springframework.transaction.annotation.Propagation#REQUIRES_NEW
     * <li>NOT_SUPPORTED</li>
     * @see org.springframework.transaction.annotation.Propagation#NOT_SUPPORTED
     * <li>NEVER</li>
     * @see org.springframework.transaction.annotation.Propagation#NEVER
     * <li>NESTED</li>
     * @see org.springframework.transaction.annotation.Propagation#NESTED
     */
    @Transactional(rollbackFor = Exception.class)
    public Object propagation(Boolean flag) {
        // propagation为外层事务

        // propagation1为内层事务，如果@Transactional注解不做处理，那么内外层事务共用一个事务，即外层事务
        try {
            // proxy.propagation1(flag);
            // proxy.propagation2(flag);
            proxy.propagation3(flag);
        } catch (BusinessException ex) {
            log.warn(ex.getMessage());
        }
        // if (flag) {
        //     // flag==true ,执行，抛异常，不回滚
        //     // flag==false ,不执行
        //     throw new RuntimeException("不回滚");
        // }
        TransactionEntity entity = new TransactionEntity();
        entity.setName(Thread.currentThread().getStackTrace()[1].getMethodName());
        entity.setAge(99);
        entity.setEmail("shenq@shenqingtech.com");
        int insert = mapper.insert(entity);
        return insert;
    }


    @Transactional(rollbackFor = Exception.class)
    public Object propagation1(Boolean flag) {
        // @Transactional注解不做特殊处理，内外层事务共用一个事务
        TransactionEntity entity = new TransactionEntity();
        entity.setName(Thread.currentThread().getStackTrace()[1].getMethodName());
        entity.setAge(28);
        entity.setEmail("zwl@shenqingtech.com");
        int insert = mapper.insert(entity);

        if (flag) {
            // 抛出异常，不管外层会不会catch这个异常，此次事务已被标记为rollback-only
            // 所以最终数据都不会入库
            throw new RuntimeException("回滚");
        }
        return insert;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Object propagation2(Boolean flag) {
        // @Transactional注解添加 propagation = Propagation.REQUIRES_NEW 属性，
        // 导致内层事务会新开一个事务，且会挂起外层事务，此时内层事务回滚不会影响外层事务（抛出的异常被catch住的时候）
        TransactionEntity entity = new TransactionEntity();
        entity.setName(Thread.currentThread().getStackTrace()[1].getMethodName());
        entity.setAge(99);
        entity.setEmail("mas@shenqingtech.com");
        int insert = mapper.insert(entity);

        if (flag) {
            // 此时内层事务抛出异常，回滚，但是外层事务不会回滚
            throw new RuntimeException("内层事务回滚");
        }
        return insert;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Object propagation3(Boolean flag) throws BusinessException {
        // @Transactional注解添加 propagation = Propagation.NESTED 属性，
        // 内层事务与外层事务共用一个事务，但是内层事务不会影响外层事务（抛出的异常被catch住的时候）
        // 如何做到不标记外层事务为rollback-only？
        // 内层事务在结束的时候会
        TransactionEntity entity = new TransactionEntity();
        entity.setName(Thread.currentThread().getStackTrace()[1].getMethodName());
        entity.setAge(28);
        entity.setEmail("mas@shenqingtech.com");
        int insert = mapper.insert(entity);

        if (flag) {
            // 内层事务回滚到savepoint
            throw new BusinessException("", "不回滚");
        }
        return insert;
    }


}
