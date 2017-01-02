/**
 * Copyright (c) 2014 - 2017 Frank Appel
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Frank Appel - initial API and implementation
 */
package com.codeaffine.eclipse.ui.progress;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;

class TableViewerAdapterHelper extends StructuredViewerAdapterHelper<TableViewer>{

  TableViewerAdapterHelper( Composite composite ) {
    super( composite );
  }

  @Override
  TableViewer createViewer( Composite parent ) {
    return new TableViewer( parent );
  }

  @Override
  ViewerAdapter createAdapter( TableViewer viewer ) {
    return new TableViewerAdapter( viewer );
  }

  @Override
  public int getItemCount() {
    return viewer.getTable().getItemCount();
  }

  @Override
  public Item getItem( int index ) {
    return viewer.getTable().getItem( index );
  }
}