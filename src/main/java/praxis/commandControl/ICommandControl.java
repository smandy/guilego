package praxis.commandControl;

import praxis.guilego.CommandControlButtonFrame;

public interface ICommandControl {
    public void securitySelectionChanged();

    void onSuspend();

    void onUnsuspend();

    public void onOptimize();

    public void kill();
    
    public void showButtonPanel();

    CommandControlButtonFrame getCommandControlButtonFrame();
}
