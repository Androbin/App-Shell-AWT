package de.androbin.shell.input;

import java.awt.event.*;

public final class AWTMouseWheelAdapter implements MouseWheelListener {
  private final MouseWheelInput input;
  
  public AWTMouseWheelAdapter( final MouseWheelInput input ) {
    this.input = input;
  }
  
  @ Override
  public void mouseWheelMoved( final MouseWheelEvent event ) {
    input.mouseWheelMoved( event.getX(), event.getY(),
        event.getWheelRotation(), (float) event.getPreciseWheelRotation() );
  }
}