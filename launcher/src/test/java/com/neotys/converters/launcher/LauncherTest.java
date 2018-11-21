package com.neotys.converters.launcher;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;
import java.io.IOException;

public class LauncherTest {

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	private static final File TARGET_DIR = Files.createTempDir();

	@Test
	public void mainTestHelp() {
		exit.expectSystemExitWithStatus(0);
		Launcher.main(ImmutableList.of("--help").toArray(new String[0]));
		Assert.fail();
	}

	@Test
	public void mainTestInvalid() {
		exit.expectSystemExitWithStatus(1);
		Launcher.main("-p test".split(" "));
		Assert.fail();
	}

	@Test
	public void mainTestNoLrProjectWithLogs() {
		exit.expectSystemExitWithStatus(2);
		final File sourceDir = Files.createTempDir();
		final String args = "-l -s " + sourceDir.getAbsolutePath() + " -t " + TARGET_DIR.getAbsolutePath() + " -p project";
		Launcher.main(args.split(" "));
		Assert.fail();
	}

	@Test
	public void mainTestInvalidCustomMapping() throws IOException {
		exit.expectSystemExitWithStatus(1);
		final File sourceDir = Files.createTempDir();
		final File tempFile = File.createTempFile("pre", "suf");
		final String args = "-m" + tempFile.getAbsolutePath() + " -s " + sourceDir.getAbsolutePath() + " -t " + TARGET_DIR.getAbsolutePath() + " -p project";
		Launcher.main(args.split(" "));
		Assert.fail();
	}

	@Test
	public void mainTestInvalidSourceFolder() {
		exit.expectSystemExitWithStatus(2);
		final String args = "-s mysource -t " + TARGET_DIR.getAbsolutePath() + " -p test";
		Launcher.main(args.split(" "));
		Assert.fail();
	}

	@Test
	public void mainTestNoSourceFolder() {
		exit.expectSystemExitWithStatus(1);
		final String args = "-s -t " + TARGET_DIR.getAbsolutePath() + " -p test";
		Launcher.main(args.split(" "));
		Assert.fail();
	}

	@Test
	public void mainTestInvalidProjectFile() {
		exit.expectSystemExitWithStatus(1);
		Launcher.main("-s mySource -t src/test/resources/projects -p test".split(" "));
		Assert.fail();
	}

	@Test
	public void mainTestInvalidEmptySourceFolder() {
		exit.expectSystemExitWithStatus(2);
		final File myTempDir = Files.createTempDir();
		final String args = "-s " + myTempDir + " -t " + TARGET_DIR.getAbsolutePath() + " -p test";
		Launcher.main(args.split(" "));
		Assert.fail();
	}

}
