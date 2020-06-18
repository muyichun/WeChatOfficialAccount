package com.xujing.pojo;

import com.xujing.pojo.music.Music;

public class MusicMessage extends BaseMessage{
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		this.Music = music;
	}
}
