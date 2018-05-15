package com.honghe.recordweb.service.frontend.mediaprobe;

import com.honghe.MediaManager.FFmpegUtil.AudioInfo;
import com.honghe.MediaManager.FFmpegUtil.MediaFileHelper;
import com.honghe.MediaManager.FFmpegUtil.MediaFileInfo;
import com.honghe.MediaManager.FFmpegUtil.VideoInfo;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hthwx on 2015/4/11.
 */
public class MediaProbeInfo {
    private final static Logger logger = Logger.getLogger(MediaProbeInfo.class);
    private MediaFileInfo mediaFileInfo;//保存扫描的视频文件夹信息
    private String FFmpegPath;//文件程序地址
    private String FilePath;//电影模式文件路径
    private String mainCode = "";//主编码方式
    private int mainSamplerate = 0;//主采样率
    private String mainResolution = "";//主分辨率
    private List<Map<String, String>> videoInfoList = new ArrayList<>(); //视频信息
    private List<Map<String, String>> audioInfoList = new ArrayList<>();//音频信息
    public List<Map<String, String>> getAudioInfoList() {
        return audioInfoList;
    }

    public void setAudioInfoList(List<Map<String, String>> audioInfoList) {
        this.audioInfoList = audioInfoList;
    }

    public List<Map<String, String>> getVideoInfoList() {
        return videoInfoList;
    }

    public void setVideoInfoList(List<Map<String, String>> videoInfoList) {
        this.videoInfoList = videoInfoList;
    }

    public String getMainResolution() {
        return mainResolution;
    }

    public void setMainResolution(String mainResolution) {
        this.mainResolution = mainResolution;
    }

    public int getMainSamplerate() {
        return mainSamplerate;
    }

    public void setMainSamplerate(int mainSamplerate) {
        this.mainSamplerate = mainSamplerate;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public MediaProbeInfo(String ffmpeg_path, String file_path) {
        this.FFmpegPath = ffmpeg_path;
        this.FilePath = file_path;
        try {
            this.mediaFileInfo = MediaFileHelper.ReadFile(this.FFmpegPath, this.FilePath);
            getAllAudioInfo();
            getAllVideoInfo();
        } catch (IOException ioe) {
//            ioe.printStackTrace();
            logger.error("read file error for MediaProbeInfo", ioe);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("no defined error for MediaProbeInfo", e);
        }
    }


    /**
     * 获取音频流信息：返回媒体文件中所有音频流信息集合
     */
    private void getAllAudioInfo() {
        List<AudioInfo> tmpList = mediaFileInfo.getAudioInfos();
        if (tmpList != null) {
            for (AudioInfo ai : tmpList) {
                Map<String, String> tMap = new HashMap<>();
                tMap.put("duration", String.valueOf(ai.getDuration() / 1000));//返回音频流时间，单位：ms。
                tMap.put("bitrate", String.valueOf(ai.getBitrate()));//返回音频流码率，单位：bps。
                tMap.put("code", ai.getCodec());//获取编码名称：
                if (null != ai.getCodec() && !("").equals(ai.getCodec().trim())) {
                    if (this.mainCode != null && !("").equals(this.mainCode.trim())) { //如果已获取视频的code为空，则采用音频code
                        this.mainCode = ai.getCodec();
                    }
                }
                tMap.put("samplerate", String.valueOf(ai.getSampleRate()));//获取采样率：
                if (ai.getSampleRate() > 0 && ai.getSampleRate() > this.mainSamplerate) {
                    this.mainSamplerate = ai.getSampleRate();//如果有多个音频轨道，则取最大值
                }
                // tMap.put("name","");//对应文件名称
                audioInfoList.add(tMap);
            }
        }
    }


    /**
     * 获取视频流信息：返回媒体文件中所有视频流信息集合
     */
    private void getAllVideoInfo() {
        List<VideoInfo> tmpList = mediaFileInfo.getVideoInfos();
        if (tmpList != null) {
            int resolutionTmp = 0;
            for (VideoInfo vi : tmpList) {
                Map<String, String> tMap = new HashMap<>();
                tMap.put("duration", String.valueOf(vi.getDuration() / 1000));//返回音频流时间，单位：ms。
                tMap.put("bitrate", String.valueOf(vi.getBitrate()));//返回音频流码率，单位：bps。
                tMap.put("code", vi.getCodec());//获取编码名称：
                if (null != vi.getCodec() && !("").equals(vi.getCodec().trim())) {
                    this.mainCode = vi.getCodec();//取任一有效视频轨道的编码
                }
                tMap.put("width", String.valueOf(vi.getWidth()));
                tMap.put("height", String.valueOf(vi.getHeight()));
                if (vi.getHeight() > 0) {
                    if (vi.getHeight() < resolutionTmp || resolutionTmp == 0) {
                        resolutionTmp = vi.getHeight();
                    }
                }
                this.mainResolution = String.valueOf(vi.getHeight());//获取有效视频轨道分辨率中最低的
                // tMap.put("name","");//对应文件名称
                videoInfoList.add(tMap);
            }
        }
    }


    /**
     * 获取媒体总时间：返回媒体文件中时长最长的媒体流时间，单位：s。
     * @return
     */
    public int getMainDuration() {
        try {
            if (mediaFileInfo != null) {
                return (int) mediaFileInfo.GetDuration() / 1000;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("GetDuration error for MediaProbeInfo", e);
        }
        return 0;
    }


    /**
     * 获取媒体总码率：返回媒体文件中所有媒体流码率之和，单位：bps。
     * @return
     */
    public int getMainBitrate() {
        try {
            if (mediaFileInfo != null) {
                return mediaFileInfo.GetBitrate();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("GetBitrate error for MediaProbeInfo", e);
        }
        return 0;
    }


}
