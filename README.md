# Montador-Assembly-Mips

Este é um montador (assembler) simples para a arquitetura MIPS, implementado em Java. O objetivo deste projeto é transformar código de montagem MIPS em código de máquina equivalente, conforme as instruções e formatos especificados.

## Funcionamento

1. **Entrada:** O montador recebe um arquivo de texto (.asm) contendo código de montagem MIPS sem erros de sintaxe. Cada instrução é escrita em uma linha separada.

2. **Identificação de Rótulos:** O montador primeiro percorre o código para identificar rótulos (labels). Rótulos são identificados por um nome seguido de dois pontos (ex: `L1:`), e suas posições de memória são registradas em uma tabela de símbolos.

3. **Tradução de Instruções:** Em seguida, o montador percorre novamente o código, traduzindo cada instrução para seu equivalente em código de máquina. Se a instrução faz referência a um rótulo, o montador substitui o rótulo pelo valor de deslocamento relativo a partir do endereço de memória inicial (0x00400000).

4. **Saída:** O montador gera um arquivo de saída em formato hexadecimal (.hex), seguindo o formato utilizado para inicializar memórias no Logisim. Cada instrução é escrita em hexadecimal, separada por um espaço. O arquivo de saída começa com o cabeçalho "v2.0 raw".


## Exemplo

Suponha que o arquivo de entrada (exemplo.asm) contenha o seguinte código:

```assembly
L1: add $t0, $s1, $s2
L2: addi $t1, $s3, 7
beq $t0, $t1, L1
j L2

Após Executar o que será gerado é:

v2.0 raw
012a4020 21490007 1109fffd 08000001

`````
## Formato do Arquivo de Saída
O arquivo de saída (.hex) segue o formato "v2.0 raw". Cada instrução é representada em hexadecimal e separada por um espaço. O montador agrupa até 4 instruções por linha, para facilitar a visualização.

## Notas
1. Este montador é uma implementação básica para fins educativos e pode não cobrir todos os casos de uso ou otimizações avançadas.

2. Certifique-se de fornecer um código de entrada válido, seguindo a sintaxe e formatos especificados.

3. Este projeto foi desenvolvido em Java. Para executá-lo, você pode precisar de um ambiente de desenvolvimento ou interpretador adequado para essa linguagem.

4. O código gerado pelo montador pode ser usado para inicializar memórias em simuladores como o Logisim.
