/********************************************************************************
 *
 * JOrocos Library
 *
 * Copyright (c) 2011
 * All rights reserved.
 *
 * Luca Gherardi
 * University of Bergamo
 * Dept. of Information Technology and Mathematics
 *
 * -------------------------------------------------------------------------------
 *
 * File: AnyObjectCast.java
 * Created: Jul 27, 2011
 *
 * Author: <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * 
 * Supervised by: <A HREF="mailto:brugali@unibg.it">Davide Brugali</A>
 * 
 * In cooperation with: <A HREF="mailto:herman.bruyninckx@mech.kuleuven.be">Herman Bruyninckx</A>
 *
 * -------------------------------------------------------------------------------
 *
 * This software is published under a dual-license: GNU Lesser General Public
 * License LGPL 2.1 and BSD license. The dual-license implies that users of this
 * code may choose which terms they prefer.
 *
 * -------------------------------------------------------------------------------
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  - Neither the name of the University of Bergamo nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License LGPL as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version or the BSD license.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License LGPL and the BSD license for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License LGPL and BSD license along with this program.
 *
 *******************************************************************************/
package it.unibg.robotics.jorocos.corba;

import it.unibg.robotics.jorocos.exceptions.SystemIpAndPortNotDefinedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.omg.CORBA.Any;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TypeCodePackage.BadKind;

/**
 * The class AnyObjectCast provides static methods for casting generic Object to
 * the CORBA {@link org.omg.CORBA.Any} type and vice versa.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public class AnyObjectCast {

	/** The logger */
	private static Logger logger = Logger.getLogger(AnyObjectCast.class);

	/**
	 * Casts a generic Object into an instance of the CORBA {@link org.omg.CORBA.Any} 
	 * type.
	 * @param value the object that has to be casted
	 * @param objectType the data type of the object that has to be casted. Typically
	 * this is the data type an the Orocos port of operation argument.
	 * @return the casted CORBA {@link org.omg.CORBA.Any} object.
	 */
	public static Any objectToAny(Object value, String objectType){

		try {
			Any any = CorbaOrocosSystem.getInstance().getORBReference().create_any();

			if(objectType.equals("bool")){

				any.insert_boolean((Boolean)value);
			}
			else if(objectType.equals("char")){

				any.insert_char((Character)value);
			}
			else if(objectType.equals("double") || objectType.equals("float64")){

				any.insert_double((Double)value);
			}
			else if(objectType.equals("float") || objectType.equals("float32")){

				any.insert_float((Float)value);
			}
			else if(objectType.equals("int") || objectType.equals("int32") ){

				any.insert_long((Integer)value);

			}
			else if(objectType.equals("uint")){

				any.insert_ulong((Integer)value);
			}
			else if(objectType.equals("string") || objectType.equals("/std/string") || objectType.equals("string const&") || objectType.equals("/std/string  const&")){

				any.insert_string((String)value);

			}else{

				String className = "";
				if(value.getClass().getCanonicalName().endsWith("[]")){
					if(value.getClass().getCanonicalName().equals("boolean[]")){
						className = "org.omg.CORBA.BooleanSeqHelper";
					}else if(value.getClass().getCanonicalName().equals("char[]")){
						className = "org.omg.CORBA.CharSeqHelper";
					}else if(value.getClass().getCanonicalName().equals("double[]")){
						className = "org.omg.CORBA.DoubleSeqHelper";
					}else if(value.getClass().getCanonicalName().equals("float[]")){
						className = "org.omg.CORBA.FloatSeqHelper";
					}else if(value.getClass().getCanonicalName().equals("int[]")){
						className = "org.omg.CORBA.LongSeqHelper";
					}else if(value.getClass().getCanonicalName().equals("java.lang.String[]")){
						className = "org.omg.CORBA.LongSeqHelper";
					}
				}else{
					//This is a complex type
					String type = value.getClass().getName();
					className = type + "Helper";
				}

				Class<?> helper = Class.forName(className);
				Method extractor = helper.getMethod("insert", Any.class, value.getClass());
				// first parameter null because the method is static
				extractor.invoke(null, any,value);
			}

			return any;
		} catch (SystemIpAndPortNotDefinedException e) {
			logger.info("You have not yet initialized the Orocos System");
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Casts a CORBA {@link org.omg.CORBA.Any} object into an instance of the  
	 * appropriate data type.
	 * 
	 * @param any the object that has to be casted
	 * @return the casted object.
	 */
	public static Object anyToObject(Any any){

		
		TypeCode type = any.type();
		
//		if(type.toString().toLowerCase().equals("boolean")){
		if(type.kind().value() == TCKind._tk_boolean){

			return any.extract_boolean();
		}
//		else if(type.toString().toLowerCase().equals("char")){
		else if(type.kind().value() == TCKind._tk_char){

			return any.extract_char();
		}
//		else if(type.toString().toLowerCase().equals("double")){
		else if(type.kind().value() == TCKind._tk_double){

			return any.extract_double();
		}
//		else if(type.toString().toLowerCase().equals("float")){
		else if(type.kind().value() == TCKind._tk_float){

			return any.extract_float();
		}
		// long is actually int in C++
//		else if(type.toString().toLowerCase().equals("long")){
		else if(type.kind().value() == TCKind._tk_long){

			return any.extract_long();
		}
//		else if(type.toString().toLowerCase().equals("ulong")){
		else if(type.kind().value() == TCKind._tk_ulong){

			return any.extract_ulong();
		}
		//else if(type.toString().toLowerCase().equals("string")||
		//		type.toString().toLowerCase().equals("/std/string")){
		else if(type.kind().value() == TCKind._tk_string){
			return any.extract_string();
		}else{
			// This is a complex type
			String anyType = "";
			try {
				anyType = type.id();
			} catch (BadKind e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int indexOfThirsColumns = anyType.indexOf(":");
			int indexOfSecondColumns = anyType.indexOf(":", indexOfThirsColumns+1);
			anyType = anyType.substring(indexOfThirsColumns + 1, indexOfSecondColumns);
			
			String className = "";
			if(anyType.contains("::")){
			  className = anyType.replace("::", ".") + "Helper";
			}else if(anyType.contains("/")){
				className = anyType.replace("/", ".") + "Helper";
			}
			if(className.startsWith("omg.org"))
				className = className.replace("omg.org", "org.omg");
			
			Object result = null;
			Class<?> helper;
			try {
				helper = Class.forName(className);
				Method extractor = helper.getMethod("extract", Any.class);
				// first parameter null because the method is static
				result = extractor.invoke(null, any);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

	}


}
