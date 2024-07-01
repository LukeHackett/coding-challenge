import { RomanNumeralConverter, RomanNumeralConverterDemo } from './RomanNumerals';

describe('CaesarCipherDemo', () => {
  let numerals: RomanNumeralConverter;

  beforeAll(() => {
    numerals = new RomanNumeralConverterDemo();
  })

  describe('#toNumerals', () => {
    it('should raise an error when given value less than 1', () => {
      // Given
      const value: number = 0;

      // When
      const wrapper = () => {
        numerals.toNumerals(value)
      };

      // Then
      expect(wrapper).toThrow('Input must be greater than 0');
    });

    it('should raise an error when given value is greater than 999', () => {
      // Given
      const value: number = 1000;

      // When
      const wrapper = () => {
        numerals.toNumerals(value)
      };

      // Then
      expect(wrapper).toThrow('Input must be less than 1000');
    });
  });
});
