package helloworld;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

class PlayThread extends Thread {
	byte tempBuffer[] = new byte[320];

	public void run() {
		try {
			int cnt;
			hasStop = false;
			// 读取数据到缓存数据
			while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
				if (isStop)
					break;
				if (cnt > 0) {
					// 写入缓存数据
					sourceDataLine.write(tempBuffer, 0, cnt);
				}
			}
			// Block等待临时数据被输出为空
			sourceDataLine.drain();
			sourceDataLine.close();
			hasStop = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}