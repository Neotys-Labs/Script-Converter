package com.neotys.converters.launcher;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

import java.io.File;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class LauncherTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

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
    public void mainTestInvalidSourceFolder() {
        exit.expectSystemExitWithStatus(2);
        Launcher.main("-s mysource -t mytarget -p test".split(" "));
        Assert.fail();
    }
    
    @Test
    public void mainTestInvalidEmptySourceFolder() {
        exit.expectSystemExitWithStatus(2);
        final File myTempDir = Files.createTempDir();
        Launcher.main(("-s " + myTempDir + " -t mytarget -p test").split(" "));
        Assert.fail();
    }

}
