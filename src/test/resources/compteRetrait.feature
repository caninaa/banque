Feature: Transaction  retrait

As a bank client
  Scenario: withdrawl 
  Given client x fait un retrai 
  When  la transaction s'execute   
  Then  le compte est alimenté
  Scenario: savemoney limite atteint
  Given client y fait un retrait 
  When  la transaction s'execute   exception est lancée
  Then  pas de changement sur le solde