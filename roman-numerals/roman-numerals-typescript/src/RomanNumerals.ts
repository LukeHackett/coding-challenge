export interface RomanNumeralConverter {
  toNumerals: (value: number) => string;
}

export class RomanNumeralConverterDemo implements RomanNumeralConverter {

  public toNumerals(value: number): string {
    throw new Error('Not Implemented');
  }

}



