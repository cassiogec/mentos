/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Fabrício Pedroso Nunes
 */
public class Logger
{
    public static void logMethod(String fileName) throws IOException
    {       
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String classLog                        = "";
        
        for (int i = 0; i < stackTraceElements.length; i++)
        {
            if (!stackTraceElements[i].getClassName().contains("Logger") && stackTraceElements[i].getClassName().contains("br.com"))
            {
                if (i != 0)
                    classLog += " >> ";
                
                classLog += stackTraceElements[i].getClassName() + "." + stackTraceElements[i].getMethodName();
            }
        }
        
        String log = classLog + System.getProperty("line.separator");
        writeEspecificLog(fileName, log);
    }
    
    public static void writeEspecificLog(String arq, String dsLog) throws IOException
    {
        String arquivo = getFileName(arq);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        Date data            = new Date();
        String dataAtual     = sdf.format(data);
        
        dsLog = dataAtual + " >> " + dsLog.replaceAll("\\n", System.getProperty("line.separator"));
        Files.write(Paths.get(arquivo), dsLog.getBytes(), StandardOpenOption.APPEND);
    }
    
    private static String getFileName(String arq) throws FileNotFoundException, UnsupportedEncodingException
    {
        String arquivo = "logs/log" + arq + ".txt";
        createLogFile(arquivo);
        return arquivo;
    }
    
    private static void createLogFile(String logFile) throws FileNotFoundException, UnsupportedEncodingException
    {
        File f = new File(logFile);
        
        if(!f.exists())
        {
            PrintWriter writer = new PrintWriter(logFile, "UTF-8");
            writer.close();
        }
    }
}
