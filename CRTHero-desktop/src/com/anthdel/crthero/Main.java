package com.anthdel.crthero;

import java.awt.Toolkit;

import com.anthdel.crthero.CRTHero;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "CRTHero";
		cfg.useGL20 = true;
		cfg.width = 1280;
		cfg.height = 720;
		cfg.resizable = false;
		
		new LwjglApplication(new CRTHero(), cfg);
	}
}
