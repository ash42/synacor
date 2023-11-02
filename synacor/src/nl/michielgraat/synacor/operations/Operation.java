package nl.michielgraat.synacor.operations;

import java.util.List;

public interface Operation {

    public int getPtrIncr();

    public List<Integer> getParams();

    public int getOpCode();

    public void execute();

    public String log();

}