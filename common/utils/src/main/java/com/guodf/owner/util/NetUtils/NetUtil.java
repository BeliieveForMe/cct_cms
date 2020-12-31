package com.guodf.owner.util.NetUtils;

import com.guodf.owner.util.LogUtils.LogUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class NetUtil {
	 public static Object execFlow(List<String> commands) {
	    	
	    	Object out = new Object();
	    	long stamp = LogUtil.getStamp();
			LogUtil.start(log, stamp, "数据沉淀工程调用Linux脚本开始************");
	    	BufferedReader br = null;
	        try {
	            String cmds = "";
	            for (String cmd : commands) {
	                cmds += cmd + ";";
	            }
	            String[] cmdA = {"/bin/sh", "-c", cmds};
	            
	            log.info("该脚本的命令为：<<" + cmds + ">>***************");
	            Process pid=null;
	            pid = Runtime.getRuntime().exec(cmdA);
	            if(pid!=null){
	            	log.info("该脚本的进程号为：<<" +pid.toString() + ">>***************");
	                br = new BufferedReader(
	                    new InputStreamReader( pid.getInputStream() ), 1024 );
	                pid.waitFor();
	            }else{
	        		LogUtil.end(log, stamp, "<<  没有pid！  >>************");
	            }
	            StringBuffer sb = new StringBuffer();
	            String line;
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	                log.info("************<<"+line+">>************");
	                sb.append(line).append("\n");
	            }
	            out = sb;
	            return out.toString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return out.toString();
	    }
	 
	    public static Object exec(String cmd) {
	    	
	    	long stamp = LogUtil.getStamp();
	    	String reg = null;
	    	StringBuffer sb = new StringBuffer();
	        try {
	            String[] cmdA = {"/bin/sh", "-c", cmd};
	            log.info("该脚本的命令为：<<" + cmd + ">>***************");
	            Process pid = Runtime.getRuntime().exec(cmdA);
	            BufferedReader br = null;
	            if(pid!=null){
	            	log.info("该脚本的进程号为：<<" +pid.toString() + ">>***************");
	                br = new BufferedReader(
	                    new InputStreamReader( pid.getInputStream() ), 1024 );
	                pid.waitFor();
	            }else{
	        		LogUtil.end(log, stamp, "<<  没有pid！  >>************");
	            }
	            String line;
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	                log.info("************<<"+line+">>************");
	                sb.append(line).append("\n");
	            }
	            reg = sb.toString();
	            return reg;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return reg;
	    }
}
