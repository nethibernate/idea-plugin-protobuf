package protobuf.settings.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;

/**
 * Facet configuration for a {@link protobuf.facet.PbFacet}.
 * @author Travis Cripps
 */
@State(
        name = "ProtobufFacetSettings",
        storages = {
                @Storage(id = "default", file = "$MODULE_FILE$"),
                @Storage(id = "dir", file = "$MODULE_CONFIG_DIR$/protobuf_facet.xml", scheme = StorageScheme.DIRECTORY_BASED)
        }
)
public class ProtobufFacetConfiguration implements FacetConfiguration, PersistentStateComponent<ProtobufFacetSettings> {

    private boolean compilationEnabled = true;
    private String compilerOutputPath = "";
    private boolean compilationUseGivenRunDirectory = false;
    private String compilationRunDirectory = "";

    @Override
    public FacetEditorTab[] createEditorTabs(FacetEditorContext editorContext, FacetValidatorsManager validatorsManager) {
        return new FacetEditorTab[] { new ProtobufFacetEditor(editorContext, validatorsManager, this) };
    }

    @Override
    public void readExternal(Element element) throws InvalidDataException {
        // deprecated
    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {
        // deprecated
    }

    @Override
    public ProtobufFacetSettings getState() {
        ProtobufFacetSettings settings = new ProtobufFacetSettings();
        settings.COMPILE_PROTO = compilationEnabled;
        settings.COMPILER_OUTPUT_SOURCE_DIRECTORY = compilerOutputPath;
	    settings.COMPILE_IN_GIVEN_DIRECTORY = compilationUseGivenRunDirectory;
	    settings.COMPILER_RUN_DIRECTORY = compilationRunDirectory;
        return settings;
    }

    @Override
    public void loadState(ProtobufFacetSettings settings) {
        compilationEnabled = settings.COMPILE_PROTO;
        compilerOutputPath = settings.COMPILER_OUTPUT_SOURCE_DIRECTORY;
	    compilationUseGivenRunDirectory = settings.COMPILE_IN_GIVEN_DIRECTORY;
	    compilationRunDirectory = settings.COMPILER_RUN_DIRECTORY;
    }

    public boolean isCompilationEnabled() {
        return compilationEnabled;
    }

    public void setIsCompilationEnabled(boolean value) {
        compilationEnabled = value;
    }

    public String getCompilerOutputPath() {
        return compilerOutputPath;
    }

    public void setCompilerOutputPath(String value) {
        compilerOutputPath = value;
    }

	public boolean isCompilationUseGivenRunDirectory() {
		return compilationUseGivenRunDirectory;
	}

	public void setCompilationUseGivenRunDirectory(boolean value) {
		this.compilationUseGivenRunDirectory = value;
	}

	public String getCompilationRunDirectory() {
		return compilationRunDirectory;
	}

	public void setCompilationRunDirectory(String value) {
		this.compilationRunDirectory = value;
	}

}
