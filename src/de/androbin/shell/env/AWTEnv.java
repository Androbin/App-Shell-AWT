package de.androbin.shell.env;

import de.androbin.gfx.*;
import de.androbin.shell.*;
import de.androbin.shell.gfx.*;
import de.androbin.shell.input.*;
import java.awt.*;
import java.awt.event.*;

public final class AWTEnv extends AbstractEnv {
  public final CustomPane canvas;
  
  public AWTEnv( final Shell shell, final int fps ) {
    super( shell, fps );
    
    final AWTGraphics graphics = (AWTGraphics) shell;
    
    canvas = new CustomPane() {
      @ Override
      public void render( final Graphics2D g ) {
        graphics.render( g );
      }
    };
    
    final Inputs inputs = shell.getInputs();
    
    if ( inputs.keyboard != null ) {
      canvas.addKeyListener( new AWTKeyAdapter( inputs.keyboard ) );
    }
    
    if ( inputs.mouse != null ) {
      canvas.addMouseListener( new AWTMouseAdapter( inputs.mouse ) );
    }
    
    if ( inputs.mouseMotion != null ) {
      canvas.addMouseMotionListener( new AWTMouseMotionAdapter( inputs.mouseMotion ) );
    }
    
    if ( inputs.mouseWheel != null ) {
      canvas.addMouseWheelListener( new AWTMouseWheelAdapter( inputs.mouseWheel ) );
    }
    
    canvas.addComponentListener( new ComponentAdapter() {
      @ Override
      public void componentResized( final ComponentEvent event ) {
        shell.setSize( canvas.getWidth(), canvas.getHeight() );
      }
    } );
  }
  
  public void render() {
    canvas.render();
  }
  
  @ Override
  public void run() {
    shell.setRunning( true );
    
    runTimed( delta -> {
      shell.update( delta );
      shell.updateUI( delta );
      render();
    } );
    
    shell.destroy();
    
    if ( shell.isDeadly() ) {
      System.exit( 0 );
    }
  }
  
  @ Override
  public void runParallel() {
    shell.setRunning( true );
    
    final Thread updateDaemon = new Thread( () -> {
      runTimed( shell::update );
    }, "AWT Update Daemon" );
    updateDaemon.setDaemon( true );
    updateDaemon.start();
    
    runTimed( delta -> {
      shell.updateUI( delta );
      render();
    } );
    
    updateDaemon.interrupt();
    
    try {
      updateDaemon.join();
    } catch ( final InterruptedException ignore ) {
    }
    
    shell.destroy();
    
    if ( shell.isDeadly() ) {
      System.exit( 0 );
    }
  }
}