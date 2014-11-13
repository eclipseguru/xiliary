package com.codeaffine.eclipse.swt.widget.scrollbar;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import com.codeaffine.eclipse.swt.widget.scrollbar.ClickControl.ClickAction;

class FastDecrementer implements ClickAction {

  private final FlatScrollBar scrollBar;

  private int x;
  private int y;

  FastDecrementer( FlatScrollBar scrollBar ) {
    this.scrollBar = scrollBar;
  }

  @Override
  public void run() {
    Rectangle drag = getDragBounds();
    Point mouse = getMouseLocation();
    if( mouse.x <= drag.x || mouse.y <= drag.y ) {
      scrollBar.setSelectionInternal( scrollBar.getSelection() - scrollBar.getPageIncrement() );
    }
  }

  @Override
  public void setCoordinates( int x, int y ) {
    this.x = x;
    this.y = y;
  }

  private Point getMouseLocation() {
    return getDisplay().map( scrollBar.upFast.getControl(), null, x, y );
  }

  private Rectangle getDragBounds() {
    Rectangle dragBounds = scrollBar.drag.getControl().getBounds();
    return getDisplay().map( scrollBar.getControl(), null, dragBounds );
  }

  private Display getDisplay() {
    return scrollBar.getControl().getDisplay();
  }
}