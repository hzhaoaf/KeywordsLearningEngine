/* 
* Copyright (C) 1997, SKTC  Ltd., 
* All Rights Reserved 
* Class name: PreProcessTarget.java 
* Description: Deal with the opinion target.  
* 
* Modification History: 
**********************************************************
* Date		           Author		    Comments
* 04  August 2011	   ZhaoHuan			Created
**********************************************************
*/

package com.skt.oms.KLE;
import java.io.*;

/** 
 * Step 2: 
 * The PreProecessTargetclass process the XML files generated by OLE system.
 * Its processed objects are the sentences of the "<Instance>" flag. 
 * it split the sentences by comma.
 */
public class PreProcessTarget {
	/**
	 
	 * The main method of PreProcessTarget. Input files and call the 
	 * PreprocessTarget	method.
	 */
	public static void Do(String strAttr, String strAttrRes, String strComp, 
			              String strCompRes, String strFuncs, 
			              String strFuncsRes,String strPhe, String strPheRes) 
	{
		try{
			BufferedWriter bufWAttr = new BufferedWriter(new 
					OutputStreamWriter(new FileOutputStream(
							strAttrRes), "UTF-8"));
			BufferedWriter bufWComp = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(strCompRes), "UTF-8"));
			BufferedWriter bufWFuncs = new BufferedWriter(new 
				OutputStreamWriter(new FileOutputStream(strFuncsRes),"UTF-8"));
			BufferedWriter bufWPhe = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(strPheRes), "UTF-8"));
			
			PreprocessTarget(strAttr, bufWAttr);
			PreprocessTarget(strComp, bufWComp);
			PreprocessTarget(strFuncs, bufWFuncs);
			PreprocessTarget(strPhe, bufWPhe);
			
			bufWAttr.close();
			bufWFuncs.close();
			bufWComp.close();
			bufWPhe.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * The PreprocessTarget method. It deal with the sentences required.
	 * It split the sentences into every line by comma.
	 */
	public static void PreprocessTarget(String strfilein, BufferedWriter bufW){
		try
		{
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(strfilein), "UTF-8"));
			String content = bufReader.readLine();
			
			while (content != null) {
				if (content.indexOf("<Instance>") != -1) {
					String newcontent = content
							.substring(content.indexOf(">") + 1);
					newcontent = newcontent.replaceAll("</Instance>", "");
					newcontent = newcontent.replaceAll(",", "\n");
					bufW.write(newcontent);
				}
				content = bufReader.readLine();
			}
			bufReader.close();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}