package protobuf.settings.facet;

import com.intellij.facet.ui.FacetEditor;
import com.intellij.facet.ui.FacetEditorsFactory;
import com.intellij.facet.ui.MultipleFacetEditorHelper;
import com.intellij.facet.ui.MultipleFacetSettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.util.NotNullFunction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * An editor for changing certain settings of multiple Protobuf facets at once.
 * @author Travis Cripps 
 */
public class ProtobufMultipleFacetSettingsEditor extends MultipleFacetSettingsEditor {

    private ProtobufFacetCommonSettingsEditor commonSettingsEditor;
    private MultipleFacetEditorHelper helper;

    public ProtobufMultipleFacetSettingsEditor(final Project project, final FacetEditor[] editors) {
        super();

        commonSettingsEditor = new ProtobufFacetCommonSettingsEditor();
        helper = FacetEditorsFactory.getInstance().createMultipleFacetEditorHelper();

        // Bind to the compilation enabled checkbox.
        helper.bind(commonSettingsEditor.getEnableCompilationCheckbox(), editors, new NotNullFunction<FacetEditor, JCheckBox>() {
            @NotNull
            public JCheckBox fun(final FacetEditor facetEditor) {
                return (facetEditor.getEditorTab(ProtobufFacetEditor.class)).getEnableCompilationCheckbox();
            }
        });

        // Bind to the output source directory text field.
        helper.bind(commonSettingsEditor.getProtobufCompilerOutputPathField().getTextField(), editors, new NotNullFunction<FacetEditor, JTextField>() {
            @NotNull
            public JTextField fun(final FacetEditor facetEditor) {
                return facetEditor.getEditorTab(ProtobufFacetEditor.class).getProtobufCompilerOutputPathField().getTextField();
            }
        });

        commonSettingsEditor.getProtobufCompilerOutputPathField().addBrowseFolderListener(project, new CompilerOutputBrowseFolderActionListener(project, null, commonSettingsEditor.getProtobufCompilerOutputPathField()));

        helper.bind(commonSettingsEditor.getProtobufCompilerRunInFixedDirectory(), editors, new NotNullFunction<FacetEditor, JCheckBox>() {
	        @NotNull
	        @Override
	        public JCheckBox fun(FacetEditor facetEditor) {
		        return (facetEditor.getEditorTab(ProtobufFacetEditor.class)).getProtoCompilerRunInFixedDirectory();
	        }
        });

	    helper.bind(commonSettingsEditor.getProtobufCompilerOutputPathField().getTextField(), editors, new NotNullFunction<FacetEditor, JTextField>() {
		    @NotNull
		    @Override
		    public JTextField fun(FacetEditor facetEditor) {
			    return facetEditor.getEditorTab(ProtobufFacetEditor.class).getProtobufCompilerOutputPathField().getTextField();
		    }
	    });

	    commonSettingsEditor.getProtobufCompilerRunDirectory().addBrowseFolderListener(project, new CompilerOutputBrowseFolderActionListener(project, null, commonSettingsEditor.getProtobufCompilerRunDirectory()));
    }

    @Override
    public JComponent createComponent() {
        return commonSettingsEditor.getMainPanel();
    }

    @Override
    public void disposeUIResources() {
        helper.unbind();
    }

}
