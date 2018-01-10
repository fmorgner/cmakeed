package com.cthing.cmakeed.parser;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.cthing.cmakeed.parser.ast.CMakeASTProvider;
import com.cthing.cmakeed.parser.ast.internal.ASTProvider;

public class CMakeParserPlugin extends Plugin {

	private static CMakeParserPlugin PLUGIN = null;
	
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
	
}
