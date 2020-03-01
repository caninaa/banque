Feature: Transaction  save money

As a bank client
  Scenario: savemoney 
  Given client x fait un depot 
  When  la transaction s'execute   
  Then  le compte est alimenté
  
    Scenario: savemoney limite atteint
  Given client x fait un depot 
  When  la transaction s'execute   exception est lancée
  