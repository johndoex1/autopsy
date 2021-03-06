/*
 * Autopsy Forensic Browser
 *
 * Copyright 2019 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.keywordsearch.multicase;

import java.awt.BorderLayout;
import java.awt.Component;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.windows.Mode;

@TopComponent.Description(
        preferredID = "MultiCaseKeywordSearchTopComponent",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "multiCaseKeywordSearch", openAtStartup = false)
@Messages({
    "CTL_MultiCaseKeywordSearchTopComponentAction=Multi-case Keyword Search",
    "CTL_MultiCaseKeywordSearchTopComponent=Multi-case Keyword Search"})
/**
 * A top level component for the multi case keyword search feature.
 */
final class MultiCaseKeywordSearchTopComponent extends TopComponent {

    public final static String PREFERRED_ID = "MultiCaseKeywordSearchTopComponent"; // NON-NLS
    private static final long serialVersionUID = 1L;
    private static boolean topComponentInitialized = false;

    @Messages({
        "MultiCaseKeywordSearchTopComponent.exceptionMessage.failedToCreatePanel=Failed to create Multi-case Keyword Search panel.",})
    /**
     * Open the top level component if it is not already open, if it is open
     * bring it to the front and select it.
     */
    static void openTopComponent() {
        final MultiCaseKeywordSearchTopComponent tc = (MultiCaseKeywordSearchTopComponent) WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (tc != null) {
            if (tc.isOpened() == false) {
                topComponentInitialized = true;
                Mode mode = WindowManager.getDefault().findMode("multiCaseKeywordSearch"); // NON-NLS
                if (mode != null) {
                    mode.dockInto(tc);
                }
                tc.open();
            }
            tc.toFront();
            tc.requestActive();
        }
    }

    /**
     * Close the top level componet.
     */
    static void closeTopComponent() {
        if (topComponentInitialized) {
            final TopComponent tc = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
            if (tc != null) {
                try {
                    tc.close();
                } catch (Exception e) {

                }
            }
        }
    }

    @Messages({"MultiCaseKeywordSearchTopComponent.name.text=Multi-case Keyword Search"})
    /**
     * Construct a new "MultiCaseKeywordSearchTopComponent.
     */
    MultiCaseKeywordSearchTopComponent() {
        initComponents();
        setName(Bundle.MultiCaseKeywordSearchTopComponent_name_text());
        setDisplayName(Bundle.MultiCaseKeywordSearchTopComponent_name_text());
        setToolTipText(Bundle.MultiCaseKeywordSearchTopComponent_name_text());
        setSize(this.getPreferredSize());
        setLayout(new BorderLayout());
        MultiCaseKeywordSearchPanel searchPanel = new MultiCaseKeywordSearchPanel();
        searchPanel.setSize(searchPanel.getPreferredSize());
        searchPanel.setVisible(true);
        add(searchPanel);
    }

    @Override
    public void componentOpened() {
        super.componentOpened();
        WindowManager.getDefault().setTopComponentFloating(this, true);
    }

    @Override
    public boolean canClose() {
        for (Component component : getComponents()) {
            if (component instanceof MultiCaseKeywordSearchPanel) {
                ((MultiCaseKeywordSearchPanel) component).closeSearchPanel();
            }
        }
        return super.canClose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(1002, 444));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 902, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
