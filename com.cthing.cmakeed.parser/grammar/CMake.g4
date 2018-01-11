grammar CMake;

@header {
package com.cthing.cmakeed.parser.llparser;
}

/**
 * Source Files
 */
file         : file_element (NEWLINE file_element)* EOF;
file_element : command_invocation line_comment? # FileElement
             | bracket_comment line_comment?    # FileElement
             | WS* line_comment?                # FileElement
             ;

/**
 * Command Invocations
 */
command_invocation  : name=identifier '(' args=arguments (WS|line_comment? NEWLINE)* ')' # CommandInvocation ;  
identifier          : id=ID ;
arguments           : argument? separated_arguments* ;
separated_arguments : (WS|line_comment? NEWLINE)+ argument          # SeparatedArguments
                    | (WS|line_comment? NEWLINE)+ '(' arguments ')' # SeparatedArguments
                    ;

/**
 * Command Arguments
 */
argument : (bracket_argument | quoted_argument | unquoted_argument) ;

/**
 * Bracket Argument
 */

bracket_argument locals [ String O = ""; ] : '[' (EQUALS { $O = $EQUALS.text; })+ '[' content=bracket_content ']' {_input.LT(1).getText().equals($O)}? EQUALS ']' # BracketArgument ;
bracket_content                            : (.|'\'')*? # BracketedContent ;

/**
 * Quoted Argument
 */
quoted_argument     : '"' quoted_element* '"' # QuotedArgument ;
quoted_element      : (escape_sequence|variable_reference|'\\' NEWLINE|~('\\'|'"')+?)+ # QuotedElement ;

/**
 * Unquoted Argument
 * 
 * TODO: implement 'unquoted_legacy'
 */
unquoted_argument : elements=unquoted_element+ # UnquotedArgument ;
unquoted_element  : variable_reference                  # UnquotedElement
                  | escape_sequence                     # UnquotedElement
                  | ~(WS|'('|')'|'#'|'"'|'\\'|NEWLINE)+ # UnquotedElement
                  ;

/**
 * Escape Sequences
 */
escape_sequence  : '\\' ~(ALPHANUM|';') # EscapeIdentity
                 | ('\\t'|'\\r'|'\\n') # EscapeEncoded
                 | '\\;' # EscapeSemicolon
                 ;

/**
 * Variable Reference
 */
variable_reference : '${' variable_name '}' # VariableReference ;
variable_name      : variable_id+                                  # VariableName
                   | variable_id* variable_reference variable_id*  # VariableName ;
variable_id        : (ID|'/'|'.'|'+'|'-'|escape_sequence) # VariableIdentifier ;

/**
 * Comments
 */
bracket_comment : '#' bracket_argument # BracketComment ;
line_comment : '#' ~(NEWLINE)*? # LineComment ;

/**
 * Lexer Rules
 */

EQUALS   : '='+ ;
WS       : [ \t];
NEWLINE  : '\r'? '\n' ;
ALPHA    : [A-Za-z_] ;
ALPHANUM : [A-Za-z0-9_] ;
ID       : ALPHA ALPHANUM+ ;
LINEEND  : (NEWLINE | EOF) ;
