package com.demus.cglib.main;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/10 19:17
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class AsmMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        generateClassByAsm();
        File file = new File("/Users/demussong/tmp");
        URL url = file.toURL();
        URLClassLoader ClassLoader=new URLClassLoader(new URL[]{ url });
        Class<?> xClass=ClassLoader.loadClass("Dock");
        Method[] declaredMethods = xClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
    }

    private static void generateClassByAsm() throws IOException {
        //ClassReader用于读取原有字节码，ClassWriter用于写入字节码，
        ClassWriter cs = new ClassWriter(0);
        //通过vist确定类的同步信息 java版本号 类修饰符 类的权限定名
        cs.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,"Duck",null, "java/lang/Object",null);
        //构造函数
        MethodVisitor mv = cs.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        // 定义code方法
        MethodVisitor methodVisitor = cs.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("I'm a Duck,Just Coding.....");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
        cs.visitEnd();
        // 使classWriter类已经完成
        // 将classWriter转换成字节数组写到文件里面去
        byte[] data = cs.toByteArray();
        File file = new File("/Users/demussong/tmp/Duck.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}