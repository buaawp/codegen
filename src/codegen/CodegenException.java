/**
 * 版权所有(C), 中国航天软件评测中心. 保留所有权利.
 * Copyright(c) 2014, China Aerospace Software Testing and Evaluation Center. All rights reserved.
 * 文   件  名: CodegenException.java
 * 功          能: 代码生成器的异常处理类，直接继承了Exception，未对异常处理内容作修改
 * 版          本: 
 * 作          者: WangPeng
 * 完成日期: 2014-07-04
 * 修   改  者:
 * 修改日期: 
 * 最后修改: 
 */

package codegen;

public class CodegenException extends Exception {

	private static final long serialVersionUID = -6890755672202154882L;

	public CodegenException(String paramString) {
		super(paramString);
	}

	public CodegenException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}

	public CodegenException(Throwable paramThrowable) {
		super(paramThrowable);
	}
}
