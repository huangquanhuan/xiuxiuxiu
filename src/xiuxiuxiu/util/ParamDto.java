package xiuxiuxiu.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

/**
 * ParamDto - 用于存储经由 commons-fileupload 解析过后的请求的结果
 * @author 刘忠燏
 */
public class ParamDto {
    /** 保存普通项的数据 */
    private Map<String, String> paramMap;
    /** 保存文件项的数据 */
    private Map<String, FileItem> fileMap;
    
    public ParamDto() {
        paramMap = new HashMap<String, String>();
        fileMap = new HashMap<String, FileItem>();
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, FileItem> getFileMap() {
        return fileMap;
    }

    public void setFileMap(Map<String, FileItem> fileMap) {
        this.fileMap = fileMap;
    }
}
