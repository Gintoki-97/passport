package cn.gin.passport.common.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

/**
 * <p>Provide a convenient conversion mode for the rest interface to return the
 * JSON data to the frontend.</p>
 */
public class JsonObject {

    /**
     * The default Json String with failed status
     */
    public static final String JSON_OBJECT_ERROR = "{success: false, code: 0, msg: 'Unknown error occurred'}";

    private boolean success;
    private int code;
    private String msg;
    private Map<String, Object> data;

    public JsonObject() {}

    /**
     * Create a JsonObject used the given fields of {@code success} and {@code msg},
     * the value of field {@code code} depends on the field {@code success}.
     *
     * <pre>
     *     success true     code 1
     *     success false    code 0
     * </pre>
     *
     * @param success Whether the request is success
     * @param msg The tips message to front end
     */
    public JsonObject(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
        this.code = success ? 1 : 0;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void addParam(String key, Object value) {

        if (data == null) {
            data = Maps.newHashMap();
        }

        data.put(key, value);
    }

    /**
     * Convert a Json Object to the Json String
     *
     * @return A Json String that corresponds to the current object.
     */
    public String toJson() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.readerFor(JsonObject.class);

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException jsonProcessingException) {
            // Nothing
        }

        return JSON_OBJECT_ERROR;
    }

    /**
     * Create a Json String used the given field {@code msg}, the field {@code success}
     * will always be true.
     *
     * @param msg The tips message to front end
     * @return A Json String with success status
     */
    public static String ok(String msg) {

        return new JsonObject(true, msg).toJson();
    }

    /**
     * Create a Json String used the given field {@code msg}, the field {@code success}
     * will always be false.
     *
     * @param msg The tips message to front end
     * @return A Json String with failed status
     */
    public static String no(String msg) {

        return new JsonObject(false, msg).toJson();
    }
}