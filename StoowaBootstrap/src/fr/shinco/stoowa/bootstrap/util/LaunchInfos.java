package fr.shinco.stoowa.bootstrap.util;

public class LaunchInfos {
    private String[] args;
    private String[] vmArgs;

    public LaunchInfos(String[] args, String[] vmArgs) {
        this.args = args;
        this.vmArgs = vmArgs;
    }

    public String[] getArgs() {
        return this.args;
    }

    public String[] getVmArgs() {
        return this.vmArgs;
    }
}
