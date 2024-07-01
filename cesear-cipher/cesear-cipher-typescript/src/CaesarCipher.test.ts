import { CaesarCipher, CaesarCipherDemo } from './CaesarCipher';

describe('CaesarCipherDemo', () => {
  let caesar: CaesarCipher;

  beforeAll(() => {
    caesar = new CaesarCipherDemo();
  })

  describe('#encode', () => {
    it('should encode a basic message', () => {
      // Given
      const message: string = "AbC";
      const shift: number = 3;

      // When
      const cipher: String = caesar.encode(message, shift);

      // Then
      expect(cipher).toBe("DEF")
    });

    it('should encode an advanced message', () => {
      // Given
      const message: string = "QT34C- 1W";
      const shift: number = 10;

      // When
      const cipher: String = caesar.encode(message, shift);

      // Then
      expect(cipher).toBe("03ABMHJ_6")
    });

  });

  describe('#decode', () => {
    it('should decode a basic message', () => {
      // Given
      const cipher: string = "DEF";
      const shift: number = 3;

      // When
      const message: String = caesar.decode(cipher, shift);

      // Then
      expect(cipher).toBe("ABC")
    });

    it('should decode an advanced message', () => {
      // Given
      const cipher: string = "A7D 4-_GD";
      const shift: number = 7;

      // When
      const message: String = caesar.decode(cipher, shift);

      // Then
      expect(cipher).toBe("HBKG_EFNK")
    });
  });
});
