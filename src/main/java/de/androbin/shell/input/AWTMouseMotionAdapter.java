package de.androbin.shell.input;

import java.awt.event.*;

public final class AWTMouseMotionAdapter implements MouseMotionListener {
  private final MouseMotionInput input;
  
  public AWTMouseMotionAdapter( final MouseMotionInput input ) {
    this.input = input;
  }
  
  @ Override
  public void mouseDragged( final MouseEvent event ) {
    input.mouseDragged( event.getX(), event.getY(), event.getButton() );
  }
  
  @ Override
  public void mouseMoved( final MouseEvent event ) {
    input.mouseMoved( event.getX(), event.getY() );
  }
}