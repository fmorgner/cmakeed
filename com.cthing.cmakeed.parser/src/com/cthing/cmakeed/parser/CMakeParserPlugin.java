package com.cthing.cmakeed.parser;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.cthing.cmakeed.parser.ast.CMakeASTProvider;
import com.cthing.cmakeed.parser.ast.internal.ASTProvider;

public class CMakeParserPlugin extends Plugin {

	private static CMakeParserPlugin PLUGIN = null;
	
	public static final String PLUGIN_ID = "com.cthing.cmakeed.parser.CMakeParserPlugin";
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		PLUGIN = this;
		context.registerService(CMakeASTProvider.class, new ASTProvider(), null);
	}
	
	public static CMakeParserPlugin getDefault() {
		return PLUGIN;
	}
	
	public static <T> T getService(Class<T> serviceType) {
		ServiceReference<T> reference = PLUGIN.getBundle().getBundleContext().getServiceReference(serviceType);
		if(reference != null) {
			return PLUGIN.getBundle().getBundleContext().getService(reference);
		}
		
		return null;
	}

	public static void logError(Throwable error) {
		logError(error, "Unexpected exception:");
	}
	
	public static void logError(Throwable error, String message) {
		ILog log = getDefault().getLog();
		log.log(new Status(IStatus.ERROR, PLUGIN_ID, message, error));
	}

}
