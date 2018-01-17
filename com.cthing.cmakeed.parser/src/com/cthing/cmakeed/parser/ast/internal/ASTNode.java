package com.cthing.cmakeed.parser.ast.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.antlr.v4.runtime.ParserRuleContext;

import com.cthing.cmakeed.parser.ast.CMakeASTNode;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeFile;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor.Decision;

abstract class ASTNode implements CMakeASTNode {

	protected final List<CMakeASTNode> fChildren = new ArrayList<>();
	protected Optional<CMakeASTNode> fParent = Optional.empty();
	
	private final String fRawSyntax;
	private final int fLine;
	private final int fStart;
	private final int fStop;

	protected ASTNode(ParserRuleContext context) {
		fRawSyntax = context.getText();
		
		fLine = context.getStart().getLine();
		fStart = context.getStart().getStartIndex();
		fStop = context.getStop().getStopIndex();
	}
	
	@Override
	public Collection<CMakeASTNode> getChildren() {
		return fChildren;
	}

	@Override
	public Optional<CMakeASTNode> getChild(int index) {
		assert(index >= 0);
		if(fChildren.size() > index) {
			return Optional.of(fChildren.get(index));
		}
		
		return Optional.empty();
	}
	
	@Override
	public Optional<CMakeASTNode> getParent() {
		return fParent;
	}

	@Override
	public final boolean accept(CMakeASTVisitor visitor) {
		final Decision decision = doAccept(visitor);
		
		if (decision == Decision.CONTINUE) {
			for (CMakeASTNode child : fChildren) {
				if (!child.accept(visitor)) {
					return false;
				}
			}
		}

		return decision != Decision.ABORT;
	}

	protected abstract Decision doAccept(CMakeASTVisitor visitor);
	
	protected ASTNode setParent(ASTNode parent) {
		if(!(parent == this)) {
			fParent = Optional.of(parent);
			parent.add(this);
		}
		return this;
	}

	protected void add(ASTNode abstractNode) {
		fChildren.add(abstractNode);
	}

	@Override
	public String getRawSyntax() {
		return fRawSyntax;
	}
	
	@Override
	public int getLineNumber() {
		return fLine;
	}
	
	@Override
	public int getStartIndex() {
		return fStart;
	}
	
	@Override
	public int getEndIndex() {
		return fStop;
	}

}
