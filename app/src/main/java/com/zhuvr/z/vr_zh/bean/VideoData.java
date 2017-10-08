package com.zhuvr.z.vr_zh.bean;

import java.io.Serializable;
import java.util.List;


public class VideoData {
    //http://media.detu.com/@/49122540-7578-ECE4-115F-D01F20618755/2016-11-13/5827d1a5b376a-2880x1440.mp4

    public String api;
    public int qtime;
    public int status;
    public String message;
    public String accessKey;
    public String voipId;
    public String voipPwd;
    public List<VideoItem> content;
    public static class VideoItem  implements Serializable {

        public String vtype;
        public String id;
        public String key;
        public String play;
        public String title;
        public String text;
        public String img;
        public String url;
        public String isbn;
        public String t0;
        public String t1;
        public String t2;
        public String userName;
        public String userNick;
        public String userPhoto;
        public String vclass;
        public int width;
        public int height;
        public double score0;
        public long date;
        public String _id;
        public String userId;
        public int cnt;
        public String type;
        public String videoChannelName;
        public String videoChannelId;
        public String textSimple;
        public String dateCnSimple;
        public List<String> tags;
    }
}
