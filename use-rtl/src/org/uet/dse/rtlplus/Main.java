package org.uet.dse.rtlplus;

import java.util.Comparator;

import org.tzi.use.runtime.IPlugin;
import org.tzi.use.runtime.IPluginRuntime;
import org.tzi.use.util.UniqueNameGenerator;
import org.uet.dse.rtlplus.matching.Match;
import org.uet.dse.rtlplus.mm.MRuleCollection;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;
import org.uet.dse.rtlplus.sync.SyncData;

public class Main implements IPlugin {
	@Override
	public String getName() {
		return "RTL plugin";
	}

	@Override
	public void run(IPluginRuntime pluginRuntime) throws Exception {
	}

	private static MRuleCollection fTggRules = new MRuleCollection(TransformationType.FORWARD);
	private static UniqueNameGenerator fUniqueNameGenerator;
	private static SyncData syncData;
	public static boolean syncWindowOpened = false;
	public static Comparator<Match> matchComparator;

	public static MRuleCollection getTggRuleCollection() {
		return fTggRules;
	}

	public static void setRTLRule(MRuleCollection rules) {
		fTggRules = rules;
		fUniqueNameGenerator = new UniqueNameGenerator();
		syncData = new SyncData(rules);
	}

	public static UniqueNameGenerator getUniqueNameGenerator() {
		return fUniqueNameGenerator;
	}
	
	public static SyncData getSyncData() {
		return syncData;
	}
	
	public static String targetMM = "";
	public static String tggFile = "";
	public static String prop = "";
	public static String sourceCT = "";
	public static String targetCT = "";
	public static int bitwidth = 8;
	public static String mapping = "";

}
