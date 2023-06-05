package com.soundsandjingles;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SoundsAndJinglesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SoundsAndJinglesPlugin.class);
		RuneLite.main(args);
	}
}