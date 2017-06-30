package cn.iven.mydemo.constant;

/**
 * Created by iven on 2017/6/19.
 */

public interface ConstUrl {
    String BASE_URL = "http://www.xxxx.com";
    String GAN_BASE_URL = "http://gank.io/api/data/";

    String GAN_LIST_URL = GAN_BASE_URL + "福利/{num}/{page}";
    String LOGIN = "session";

    String GET_TO_LEARN = "v1/default/index";
}