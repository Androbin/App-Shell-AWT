package de.androbin.shell.input;

import java.awt.event.*;

public final class AWTMouseAdapter implements MouseListener {
  private final MouseInput input;
  
  public AWTMouseAdapter( final MouseInput input ) {
    this.input = input;
  }
  
  @ Override
  public void mouseClicked( final MouseEvent event ) {
    input.mouseClicked( event.getX(), event.getY(), event.getButton() );
  }
  
  @ Override
  public void mouseEntered( final MouseEvent event ) {
  }
  
  @ Override
  public void mouseExited( final MouseEvent event ) {
  }
  
  @ Override
  public void mousePressed( final MouseEvent event ) {
    input.mousePressed( event.getX(), event.getY(), event.getButton() );
  }
  
  @ Override
  public void mouseReleased( final MouseEvent event ) {
    input.mouseReleased( event.getX(), event.getY(), event.getButton() );
  }
}