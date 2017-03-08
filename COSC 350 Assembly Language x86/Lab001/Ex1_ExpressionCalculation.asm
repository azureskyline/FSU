; Chapter 3, Exercise 1: Expression Calculation

Comment !
Description : Using the AddTwoo program from Section 3.2 as a
	reference, write a program that calculates the following expression,
	using registers : A = (A + B) - (C + D).Assign integer values to the EAX,
	EBX, ECX, and EDX registers.
	!

	.386
	.model flat, stdcall
	.stack 4096
	ExitProcess proto, dwExitCode:dword
	.code
	main PROC

	mov	ax, 4000h
	mov	bx, 1000h
	mov	cx, 1500h

	sub	ax,bx
	sub	ax,cx

	INVOKE ExitProcess,0
main ENDP
END main