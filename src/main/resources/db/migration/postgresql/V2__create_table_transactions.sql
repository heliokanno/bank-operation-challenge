create table "transactions"
(
    "transaction_id" uuid not null constraint transactions_pk primary key,
    "source_account_id" uuid null,
    "target_account_id" uuid null,
    "num_amount" numeric(38,2) not null,
    "dat_created_at" timestamp(6) not null,
    constraint fk_source_account_id foreign key("source_account_id") references "accounts"("account_id"),
    constraint fk_target_account_id foreign key("target_account_id") references "accounts"("account_id")
);
