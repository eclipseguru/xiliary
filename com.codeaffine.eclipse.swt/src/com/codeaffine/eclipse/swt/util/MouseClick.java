package com.codeaffine.eclipse.swt.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

public class MouseClick {

  private boolean armed;

  public boolean isArmed() {
    return armed;
  }

  public void arm( MouseEvent event ) {
    if( ( event.button & SWT.BUTTON1 ) > 0 ) {
      armed = true;
    }
  }

  public void trigger( MouseEvent event, Runnable action ) {
    if( armed && inRange( event ) ) {
      action.run();
    }
    armed = false;
  }

  private static boolean inRange( MouseEvent event ) {
    Point size = ( ( Control )event.widget ).getSize();
    return event.x >= 0 && event.x <= size.x && event.y >= 0 && event.y <= size.y;
  }
}