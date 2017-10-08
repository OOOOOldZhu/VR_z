package com.zhuvr.z.vr_zh.bean;

import java.io.Serializable;
import java.util.List;

public class ImageData {

    public List<ImageItem> list;

    public static class ImageItem implements Serializable {
        /**
         * mp3 : http://media.qicdn.detu.com/@/13363707-8857-C248-3CE1-64F2F24291636/source/145049/o_1arbdk2apj37df16up16um196j7.mp3
         * title : 滕王阁
         * url : http://media.qicdn.detu.com/pano177051472357986990056825/thumb/500_500/panofile.jpg
         */
        public String mp3;
        public String title;
        public String url;
    }
}
