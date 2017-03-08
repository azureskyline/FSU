; AustriaCatherine_L001.asm - adds two 32-bit integers.
; L001 Wed Feb 15,2017

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.code
main proc
	mov	eax,23			
	mov ebx,46
	mov ecx,12
	mov edx,15
	
	add	eax, ebx
	add ecx, edx
	sub eax, ecx

	invoke ExitProcess,0
main endp
end main