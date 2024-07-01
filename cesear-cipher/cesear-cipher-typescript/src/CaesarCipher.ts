export interface CaesarCipher {
  encode: (message: string, shift: number) => string;
  decode: (cipher: string, shift: number) => string;
}

export class CaesarCipherDemo implements CaesarCipher {

  public encode(message: string, shift: number): string {
    throw new Error('Not Implemented');
  };

  public decode(cipher: string, shift: number): string {
    throw new Error('Not Implemented');
  }

}