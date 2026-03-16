package com.architecture.model.service;
import com.architecture.App;
import com.architecture.model.entity.GameState;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

//音乐的类型和mapState与currentState的值密切相关
public class AudioService {
    /*功能：循环播放背景音乐
    参数：mapState,currentState
    返回值：无
    */
    private boolean isMusicPlaying = false;
    private String currentMusic = "";
    private Clip bgmClip; // 背景音乐Clip
    private Clip soundClip; // 音效Clip
    private boolean isMusicEnabled = true;       // 默认开启背景音乐
    private boolean isSoundEffectEnabled = true; // 默认开启音效


    public static final String MENU_MUSIC = "/audio/menu.wav";//菜单音乐
    public static final String MAP_MUSIC_DAY = "/audio/map.wav";//地图音乐
    public static final String BATTLE_MUSIC = "/audio/battle.wav";//战斗音乐
    public static final String SHOP_MUSIC = "/audio/shop.wav";
    public static final String BOSS_MUSIC = "/audio/boss.wav";// 打boss的音乐
    public static final String MAP_MUSIC_NIGHT = "/audio/map2.wav";//胜利音乐


    public static final String MOVE_SOUND = "/audio/move.wav";//移动音效
    public static final String ATTACK_SOUND = "/audio/attack.wav";//攻击音效
    public static final String HURT_SOUND = "/audio/hurt.wav";//受伤音效
    public static final String PICK_ITEM_SOUND = "/audio/pick.wav";//拾取物品音效
    public static final String SHOP_BUY_SOUND = "/audio/shop.wav";//购买音效

    public void musicOn(int mapState, GameState currentState) {
        if (!isMusicEnabled) {
            return;
        }

        String targetMusic = "";
        switch (currentState) {
            case MENU:
                targetMusic = MENU_MUSIC;
                break;
            case MAP:
                // 根据mapState
                if (mapState == 1 || mapState == 2 || mapState == 3 || mapState == 4) {
                    targetMusic = MAP_MUSIC_DAY;

                } else if (mapState == 5) {
                    targetMusic = MAP_MUSIC_NIGHT; // BOSS区域地图背景音乐
                } else if (mapState == 3) {

                } else if (mapState == 4) {

                } else if (mapState == 5) {

                }
                break;
            case BATTLE:
                if (App.currentEnemy != null && App.currentEnemy.getName().contains("[Boss]")) {
                    targetMusic = BOSS_MUSIC;
                } else {
                    targetMusic = BATTLE_MUSIC;
                }
                break;
            case SHOP:
                targetMusic = SHOP_MUSIC;
                break;
            default:
                targetMusic = MENU_MUSIC; // 默认播放菜单音乐
        }
        if (!targetMusic.equals(currentMusic) || !isMusicPlaying) {
            stopCurrentMusic(); // 停止当前音乐
            playMusic(targetMusic); // 播放新音乐
            currentMusic = targetMusic;
            isMusicPlaying = true;
        }
    }

    /*功能：停止音乐播放
    参数：mapState,currentState
    返回值：无
     */
    public void musicOff() {
        stopCurrentMusic();
        currentMusic = "";
        isMusicPlaying = false;
    }

    /*功能：播放音效
    参数：mapState,currentState
    返回值：无
     */

    public void soundEffectOn(String soundType) {
        String soundPath = "";
        switch (soundType) {
            case "move":
                soundPath = MOVE_SOUND;
                break;
            case "attack":
                soundPath = ATTACK_SOUND;
                break;
            case "hurt":
                soundPath = HURT_SOUND;
                break;
            case "pick":
                soundPath = PICK_ITEM_SOUND;
                break;
            case "buy":
                soundPath = SHOP_BUY_SOUND;
                break;
            default:
                return; // 无效音效类型，直接返回
        }
        playSoundEffect(soundPath);
    }

    /*功能：关闭音效
    参数：mapState,currentState
    返回值：无
    */
    public void soundEffectOff(){
        if (soundClip != null && soundClip.isRunning()) {
            soundClip.stop();  // 停止音效播放
            soundClip.close(); // 释放音效资源
        }
    }




    /*功能：调用getTile，判断一下出口类型来改变全局mapState,要询问地图设计者的地图设计来定mapState怎么变
        参数:无
        返回值：无
     */
    public void changeMap() {
        if (App.player == null || App.mapService == null) {
            return;
        }

        App.mapState = 0;
        // 播放白天对应的音乐
        musicOn(App.mapState, App.currentState);
    }

    public void playMusic(String musicPath) {
        try {
            URL musicUrl = getClass().getResource(musicPath);
            if (musicUrl == null) {
                System.out.println("背景音乐文件不存在：" + musicPath);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicUrl);
            bgmClip = AudioSystem.getClip();
            bgmClip.open(audioStream);
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgmClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("播放背景音乐失败：" + e.getMessage());
        }
    }

    //暂停播放
    public void stopCurrentMusic() {
        if (bgmClip != null && bgmClip.isRunning()) {
            bgmClip.stop();
            bgmClip.close();
        }
        isMusicPlaying = false;
    }

    //播放音效
    public void playSoundEffect(String soundPath) {
        if (!isSoundEffectEnabled) {
            return;
        }
        new Thread(() -> {
            try {
                URL soundUrl = getClass().getResource(soundPath);
                if (soundUrl == null) {
                    System.out.println("音效文件不存在：" + soundPath);
                    return;
                }
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);

                // 使用局部变量 localClip 处理音效，防止多个短音效同时播放时出现指针冲突导致被提前切断
                Clip localClip = AudioSystem.getClip();
                localClip.open(audioStream);
                localClip.start();

                // 将局部变量赋给全局变量，以保证 soundEffectOff() 仍能停止最后一次播放的音效
                soundClip = localClip;

                localClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        localClip.close();
                    }
                });
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println("播放音效失败：" + e.getMessage());
            }
        }).start();
    }

    // 音乐总开关
    public void setMusicEnabled(boolean enabled) {
        this.isMusicEnabled = enabled;
        if (!enabled) {
            musicOff(); // 如果设置为关闭，立即停止当前正在播放的音乐
        }
    }

    // 音效总开关
    public void setSoundEffectEnabled(boolean enabled) {
        this.isSoundEffectEnabled = enabled;
        if (!enabled) {
            soundEffectOff(); // 如果设置为关闭，立即切断当前正在播放的音效
        }
    }

    //根据地图格子类型判断出口类型
    public String getExitTypeByTile() {
        return "DAY";
    }
}
