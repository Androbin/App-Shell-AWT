package de.androbin.shell.input;

import java.awt.event.*;

public final class AWTKeyAdapter implements KeyListener {
  private final KeyInput input;
  
  public AWTKeyAdapter( final KeyInput input ) {
    this.input = input;
  }
  
  @ Override
  public void keyPressed( final KeyEvent event ) {
    input.keyPressed( event.getKeyCode() );
  }
  
  @ Override
  public void keyReleased( final KeyEvent event ) {
    input.keyReleased( event.getKeyCode() );
  }
  
  @ Override
  public void keyTyped( final KeyEvent event ) {
    input.keyTyped( event.getKeyChar() );
  }
}