package src.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_SendUser implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //here smt like await
        if(!GUI_ConnectionToServer.isConnected())
        {
            GUI_Operations.registration();
        }
    }
}