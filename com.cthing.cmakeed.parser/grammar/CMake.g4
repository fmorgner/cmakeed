grammar CMake;

@header {
package com.cthing.cmakeed.parser.llparser;
}

/**
 * Source Files
 */
file         : file_element* ;
file_element : command_invocation line_ending
             | (bracket_comment|WS)* line_ending
             ;
line_ending  : line_comment? NEWLINE ;

/**
 * Command Invocations
 */
command_invocation  : identifier '(' arguments (WS|line_ending)* ')' ;
identifier          : ID ;
arguments           : argument? separated_arguments* ;
separated_arguments : (WS|line_ending)+ argument
                    | (WS|line_ending)+ '(' arguments ')'
                    ;

/**
 * Command Arguments
 */
argument : (bracket_argument | quoted_argument | unquoted_argument) ;

/**
 * Bracket Argument
 */

bracket_argument locals [ String O = ""; ] : '[' (EQUALS { $O = $EQUALS.text; })+ '[' content=bracket_content ']' {_input.LT(1).getText().equals($O)}? EQUALS ']' ;
bracket_content                            : (.|'\'')*? ;

/**
 * Quoted Argument
 */
quoted_argument     : '"' quoted_element* '"' ;
quoted_element      : (escape_sequence|variable_reference|quoted_continuation|~('\\'|'"')+?)+
                    // | escape_sequence
                    // | variable_reference
                    // | quoted_continuation
                    ;
quoted_continuation : '\\' NEWLINE ;

/**
 * Unquoted Argument
 * 
 * TODO: implement 'unquoted_legacy'
 */
unquoted_argument : unquoted_element+ ;
unquoted_element  : ~(WS|'('|')'|'#'|'"'|'\\'|NEWLINE)+
                  | escape_sequence
                  ;

/**
 * Escape Sequences
 */
escape_sequence  : escape_identity
                 | escape_encoded
                 | escape_semicolon
                 ;
escape_identity  : '\\' ~(ALPHANUM|';') ;
escape_encoded   : '\\t'
                 | '\\r'
                 | '\\n'
                 ;
escape_semicolon : '\\;' ;

/**
 * Variable Reference
 */
variable_reference : '${' variable_name '}' ;
variable_name      : variable_id+
                   | variable_id* variable_reference variable_id* ;
variable_id        : (ID|'/'|'.'|'+'|'-'|escape_sequence) ;

/**
 * Comments
 */
bracket_comment : '#' bracket_argument ;
line_comment : '#' ~(NEWLINE)*? ;

/**
 * Lexer Rules
 */

EQUALS   : '='+ ;
WS       : [ \t];
NEWLINE  : '\r'? '\n' ;
ALPHA    : [A-Za-z_] ;
ALPHANUM : [A-Za-z0-9_] ;
ID       : ALPHA ALPHANUM+ ;
