package com.example.kt_coroutines.t82;

import androidx.annotation.NonNull;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/**
 * @author yanguoqing
 * @data 2024/7/25
 * Description:
 */
public class JavaTest {
    public static void main(String[] args) {
        Object object = SuspendTypeKt.action1Suspend(11.1, new Continuation<String>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NonNull Object o) {
                System.out.println("action1Suspend 挂起函数执行完成了 resume结果是：" +o);
            }
        });
        System.out.println("object:" + object);

    }
}
