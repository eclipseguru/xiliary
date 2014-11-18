package com.codeaffine.eclipse.swt.widget.scrollbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.codeaffine.eclipse.swt.util.MouseClick;
import com.codeaffine.eclipse.swt.util.MouseDownActionTimer;
import com.codeaffine.eclipse.swt.util.MouseDownActionTimer.TimerAction;

class ClickControl implements ViewComponent, TimerAction, MouseListener, MouseTrackListener {

  private final Label control;
  private final MouseClick mouseClick;
  private final MouseDownActionTimer mouseDownActionTimer;
  private final ClickAction clickAction;

  public interface ClickAction extends Runnable {
    void setCoordinates( int x, int y );
  }

  ClickControl( Composite parent, Color background, ClickAction clickAction ) {
    this.control = new Label( parent, SWT.NONE );
    this.control.setBackground( background );
    this.mouseClick = new MouseClick();
    this.mouseDownActionTimer = new MouseDownActionTimer( this, mouseClick, control.getDisplay() );
    this.clickAction = clickAction;
    this.control.addMouseListener( this );
    this.control.addMouseTrackListener( this );
  }

  @Override
  public Control getControl() {
    return control;
  }

  @Override
  public void mouseDown( MouseEvent event ) {
    mouseClick.arm( event );
    clickAction.setCoordinates( event.x, event.y );
    mouseDownActionTimer.activate();
  }

  @Override
  public void mouseUp( MouseEvent event ) {
    mouseClick.trigger( event, clickAction );
  }

  @Override
  public void run() {
    clickAction.run();
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public void mouseExit( MouseEvent event ) {
    mouseClick.disarm();
  }

  @Override
  public void mouseEnter( MouseEvent event ) {}

  @Override
  public void mouseHover( MouseEvent event ) {}

  @Override
  public void mouseDoubleClick( MouseEvent event ) {}
}