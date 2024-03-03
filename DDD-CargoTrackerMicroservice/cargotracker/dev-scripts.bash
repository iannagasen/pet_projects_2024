spring init \
  --boot-version=3.2.0 \
  --type=gradle-project \
  --java-version=17 \
  --packaging=jar \
  --name=booking-ms \
  --package-name="dev.agasen.cargo.booking" \
  --groupId="dev.agasen.cargo.booking" \
  --dependencies=web,data-jpa,mysql,validation,lombok \
  --version=0.0.1-SNAPSHOT \
  booking-ms