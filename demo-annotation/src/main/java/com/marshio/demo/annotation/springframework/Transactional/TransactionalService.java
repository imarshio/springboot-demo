package com.marshio.demo.annotation.springframework.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author marshio
 * @desc Transactional注解的使用
 * @create 2023-12-26 16:33
 */
@Service
public class TransactionalService {

    private final TransactionMapper mapper;

    public TransactionalService(TransactionMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("all")
    public Object rollback1(Boolean flag) {
        TransactionEntity entity = new TransactionEntity();
        entity.setName("ma");
        entity.setAge(25);
        entity.setEmail("mashuo@shenqingtech.com");
        int insert = mapper.insert(entity);
        if (flag) {
            // true 执行，报错 ArithmeticException，回滚
            // false 不执行，不报错 ，不回滚
            return insert / 0;
        }
        return insert;
    }

    @Transactional(rollbackFor = BusinessException.class)
    @SuppressWarnings("all")
    public Object rollback2(Boolean flag) {
        TransactionEntity entity = new TransactionEntity();
        entity.setName("zwl");
        entity.setAge(28);
        entity.setEmail("zwl@shenqingtech.com");
        int insert = mapper.insert(entity);

        if (!flag) {
            // flag==true ,不执行
            // flag==false ,执行，报错 ArithmeticException 回滚
            int result = insert / 0;
        }
        if (flag) {
            // flag==true ,执行，抛异常，不回滚
            // flag==false ,不执行
            throw new BusinessException("code", "不回滚");
        }
        return insert;
    }


    @Transactional(rollbackFor = Exception.class, noRollbackFor = ArithmeticException.class)
    @SuppressWarnings("all")
    public Object noRollback1(Boolean flag) {
        TransactionEntity entity = new TransactionEntity();
        entity.setName("zwl");
        entity.setAge(28);
        entity.setEmail("zwl@shenqingtech.com");
        int insert = mapper.insert(entity);

        if (!flag) {
            // flag==true ,不执行
            // flag==false ,执行，报错 ArithmeticException 回滚
            int result = insert / 0;
        }
        if (flag) {
            // flag==true ,执行，抛异常，不回滚
            // flag==false ,不执行
            throw new RuntimeException("不回滚");
        }
        return insert;
    }


}
